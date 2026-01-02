package top.potat.spring.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.potat.spring.admin.component.delay.AutoDelayQueueManager;
import top.potat.spring.admin.component.delay.DelayedElement;
import top.potat.spring.admin.component.delay.DelayedMsgRes;
import top.potat.spring.admin.component.event.EventAsyncReq;
import top.potat.spring.admin.component.event.EventReq;
import top.potat.spring.admin.component.event.EventSendComponent;
import top.potat.spring.common.config.redis.RedisUtils;
import top.potat.spring.common.constant.RedisConstant;
import top.potat.spring.common.utils.AddressUtils;
import top.potat.spring.common.utils.StringUtils;
import top.potat.spring.common.utils.encryption.PasswordEncoder;
import top.potat.spring.common.utils.result.CommonResult;
import top.potat.spring.db.models.TestModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测试接口
 */
@Api(tags = "00001测试")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController extends BaseController{
    private final RedisUtils redisUtils;
    private final EventSendComponent eventSendComponent;
    private final AutoDelayQueueManager autoDelayQueueManager;


    @ApiOperation("测试接口")
    @GetMapping("/test")
    public CommonResult<String> test(){

        return CommonResult.success("OK");
    }

    @ApiOperation("测试Post接口")
    @PostMapping("/testPost")
    public CommonResult<TestModel.GetTest> testPost(@RequestBody TestModel.GetTest getTest){

        return CommonResult.success(getTest);
    }

    @ApiOperation("多张图片上传")
    @PostMapping("/upload_one")
    public CommonResult uploadImage(@RequestParam(value = "images",required = false) List<MultipartFile> files){
        if (files == null || files.isEmpty()) return CommonResult.validateFailed("请选择图片上传");

        return CommonResult.success(null);
    }

    @ApiOperation("获取IP测试接口")
    @GetMapping("/getIpTest")
    public CommonResult<String> getIpTest(HttpServletRequest request){

        return CommonResult.success(AddressUtils.getIpAddress(request));
    }

    @ApiOperation("redis测试接口")
    @GetMapping("/redisTest")
    public CommonResult redisSetGetTest(){
        redisUtils.set(RedisConstant.TEST_REDIS_KEY,1);
        return CommonResult.success(redisUtils.get("test"));
    }

    @ApiOperation("密码测试")
    @GetMapping("/passTest")
    public CommonResult passTest(String password){
        if(StringUtils.isEmpty(password))
            return CommonResult.failed("密码不能为空");
        long startTime = System.currentTimeMillis();
        String testPassword = PasswordEncoder.encrypt("testPassword");
        long endTime = System.currentTimeMillis();
        System.out.println("单次加密耗时: " + (endTime - startTime) + "ms");
        return CommonResult.success(testPassword);
    }


    @ApiOperation(value = "获取内存")
    @GetMapping("/testRun")
    public CommonResult<Long> testRun(){
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        logger.debug("总 Memory: {} bytes", totalMemory);
        logger.debug("空闲 Memory: {} bytes", freeMemory);
        logger.debug("使用 Memory: {} bytes", usedMemory);
        try {
            usedMemory = (usedMemory / (1024 * 1024));
            logger.debug("总: ->{}M", totalMemory / (1024 * 1024));
            logger.debug("空闲: ->{}M", freeMemory / (1024 * 1024));
            logger.debug("使用: ->{}M", usedMemory);
        } catch (Exception ignored) {
            usedMemory = totalMemory - freeMemory;
        }
        return CommonResult.success(usedMemory, "使用内存情况(M)");
    }


    @ApiOperation(value = "测试异步事件")
    @GetMapping("/testAsyncEvent")
    public CommonResult<String> testAsyncEvent(){
        EventAsyncReq eventAsyncReq = new EventAsyncReq(this, "test", "");
        eventSendComponent.sendAsyncEvent(eventAsyncReq);
        return CommonResult.success("OK");
    }

    @ApiOperation(value = "测试事件")
    @GetMapping("/testEvent")
    public CommonResult<String> testEvent(){
        EventReq eventReq = new EventReq(this, "test", "");
        eventSendComponent.sendEvent(eventReq);
        return CommonResult.success("OK");
    }


    @ApiOperation(value = "测试延迟事件")
    @GetMapping("/testDelayEvent")
    public CommonResult<String> testDelayEvent(){
        autoDelayQueueManager.put(DelayedElement.init(new DelayedMsgRes("test", ""),10));
        return CommonResult.success("OK");
    }


}
