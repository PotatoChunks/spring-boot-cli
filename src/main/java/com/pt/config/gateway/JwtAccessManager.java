package com.pt.config.gateway;

/**
 * 鉴权管理器自定义
 */
//@Component
public class JwtAccessManager /*implements ReactiveAuthorizationManager<AuthorizationContext>*/ {
    /*@Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        return Mono.just(new AuthorizationDecision(true));
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //非管理端路径直接放行
        if (!pathMatcher.match(MyConstant.ADMIN_URL_PATH + "/**", uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }

        return mono
                .map(role -> role.equals("ADMIN")) // 检查用户角色是否为管理员（允许访问特定资源）
                .map(AuthorizationDecision::new) // 创建AuthorizationDecision对象
                .defaultIfEmpty(new AuthorizationDecision(false)); // 如果用户角色不是管理员，默认返回false（不允许访问）
    }*/
}
