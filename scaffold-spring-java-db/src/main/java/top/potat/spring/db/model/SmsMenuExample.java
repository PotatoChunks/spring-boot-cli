package top.potat.spring.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmsMenuExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdIsNull() {
            addCriterion("menu_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdIsNotNull() {
            addCriterion("menu_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdEqualTo(Long value) {
            addCriterion("menu_parent_id =", value, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdNotEqualTo(Long value) {
            addCriterion("menu_parent_id <>", value, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdGreaterThan(Long value) {
            addCriterion("menu_parent_id >", value, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("menu_parent_id >=", value, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdLessThan(Long value) {
            addCriterion("menu_parent_id <", value, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdLessThanOrEqualTo(Long value) {
            addCriterion("menu_parent_id <=", value, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdIn(List<Long> values) {
            addCriterion("menu_parent_id in", values, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdNotIn(List<Long> values) {
            addCriterion("menu_parent_id not in", values, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdBetween(Long value1, Long value2) {
            addCriterion("menu_parent_id between", value1, value2, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuParentIdNotBetween(Long value1, Long value2) {
            addCriterion("menu_parent_id not between", value1, value2, "menuParentId");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrIsNull() {
            addCriterion("menu_level_str is null");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrIsNotNull() {
            addCriterion("menu_level_str is not null");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrEqualTo(String value) {
            addCriterion("menu_level_str =", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrNotEqualTo(String value) {
            addCriterion("menu_level_str <>", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrGreaterThan(String value) {
            addCriterion("menu_level_str >", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrGreaterThanOrEqualTo(String value) {
            addCriterion("menu_level_str >=", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrLessThan(String value) {
            addCriterion("menu_level_str <", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrLessThanOrEqualTo(String value) {
            addCriterion("menu_level_str <=", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrLike(String value) {
            addCriterion("menu_level_str like", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrNotLike(String value) {
            addCriterion("menu_level_str not like", value, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrIn(List<String> values) {
            addCriterion("menu_level_str in", values, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrNotIn(List<String> values) {
            addCriterion("menu_level_str not in", values, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrBetween(String value1, String value2) {
            addCriterion("menu_level_str between", value1, value2, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuLevelStrNotBetween(String value1, String value2) {
            addCriterion("menu_level_str not between", value1, value2, "menuLevelStr");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("menu_name is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("menu_name is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("menu_name =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("menu_name <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("menu_name >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("menu_name >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("menu_name <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("menu_name <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("menu_name like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("menu_name not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("menu_name in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("menu_name not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("menu_name between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("menu_name not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuIconIsNull() {
            addCriterion("menu_icon is null");
            return (Criteria) this;
        }

        public Criteria andMenuIconIsNotNull() {
            addCriterion("menu_icon is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIconEqualTo(String value) {
            addCriterion("menu_icon =", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotEqualTo(String value) {
            addCriterion("menu_icon <>", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconGreaterThan(String value) {
            addCriterion("menu_icon >", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconGreaterThanOrEqualTo(String value) {
            addCriterion("menu_icon >=", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLessThan(String value) {
            addCriterion("menu_icon <", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLessThanOrEqualTo(String value) {
            addCriterion("menu_icon <=", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLike(String value) {
            addCriterion("menu_icon like", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotLike(String value) {
            addCriterion("menu_icon not like", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconIn(List<String> values) {
            addCriterion("menu_icon in", values, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotIn(List<String> values) {
            addCriterion("menu_icon not in", values, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconBetween(String value1, String value2) {
            addCriterion("menu_icon between", value1, value2, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotBetween(String value1, String value2) {
            addCriterion("menu_icon not between", value1, value2, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuPathIsNull() {
            addCriterion("menu_path is null");
            return (Criteria) this;
        }

        public Criteria andMenuPathIsNotNull() {
            addCriterion("menu_path is not null");
            return (Criteria) this;
        }

        public Criteria andMenuPathEqualTo(String value) {
            addCriterion("menu_path =", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotEqualTo(String value) {
            addCriterion("menu_path <>", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathGreaterThan(String value) {
            addCriterion("menu_path >", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathGreaterThanOrEqualTo(String value) {
            addCriterion("menu_path >=", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathLessThan(String value) {
            addCriterion("menu_path <", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathLessThanOrEqualTo(String value) {
            addCriterion("menu_path <=", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathLike(String value) {
            addCriterion("menu_path like", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotLike(String value) {
            addCriterion("menu_path not like", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathIn(List<String> values) {
            addCriterion("menu_path in", values, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotIn(List<String> values) {
            addCriterion("menu_path not in", values, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathBetween(String value1, String value2) {
            addCriterion("menu_path between", value1, value2, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotBetween(String value1, String value2) {
            addCriterion("menu_path not between", value1, value2, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionIsNull() {
            addCriterion("menu_description is null");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionIsNotNull() {
            addCriterion("menu_description is not null");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionEqualTo(String value) {
            addCriterion("menu_description =", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotEqualTo(String value) {
            addCriterion("menu_description <>", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionGreaterThan(String value) {
            addCriterion("menu_description >", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("menu_description >=", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionLessThan(String value) {
            addCriterion("menu_description <", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionLessThanOrEqualTo(String value) {
            addCriterion("menu_description <=", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionLike(String value) {
            addCriterion("menu_description like", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotLike(String value) {
            addCriterion("menu_description not like", value, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionIn(List<String> values) {
            addCriterion("menu_description in", values, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotIn(List<String> values) {
            addCriterion("menu_description not in", values, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionBetween(String value1, String value2) {
            addCriterion("menu_description between", value1, value2, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuDescriptionNotBetween(String value1, String value2) {
            addCriterion("menu_description not between", value1, value2, "menuDescription");
            return (Criteria) this;
        }

        public Criteria andMenuSortIsNull() {
            addCriterion("menu_sort is null");
            return (Criteria) this;
        }

        public Criteria andMenuSortIsNotNull() {
            addCriterion("menu_sort is not null");
            return (Criteria) this;
        }

        public Criteria andMenuSortEqualTo(Integer value) {
            addCriterion("menu_sort =", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotEqualTo(Integer value) {
            addCriterion("menu_sort <>", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortGreaterThan(Integer value) {
            addCriterion("menu_sort >", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_sort >=", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortLessThan(Integer value) {
            addCriterion("menu_sort <", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortLessThanOrEqualTo(Integer value) {
            addCriterion("menu_sort <=", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortIn(List<Integer> values) {
            addCriterion("menu_sort in", values, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotIn(List<Integer> values) {
            addCriterion("menu_sort not in", values, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortBetween(Integer value1, Integer value2) {
            addCriterion("menu_sort between", value1, value2, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_sort not between", value1, value2, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuStatusIsNull() {
            addCriterion("menu_status is null");
            return (Criteria) this;
        }

        public Criteria andMenuStatusIsNotNull() {
            addCriterion("menu_status is not null");
            return (Criteria) this;
        }

        public Criteria andMenuStatusEqualTo(Integer value) {
            addCriterion("menu_status =", value, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusNotEqualTo(Integer value) {
            addCriterion("menu_status <>", value, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusGreaterThan(Integer value) {
            addCriterion("menu_status >", value, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_status >=", value, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusLessThan(Integer value) {
            addCriterion("menu_status <", value, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusLessThanOrEqualTo(Integer value) {
            addCriterion("menu_status <=", value, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusIn(List<Integer> values) {
            addCriterion("menu_status in", values, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusNotIn(List<Integer> values) {
            addCriterion("menu_status not in", values, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusBetween(Integer value1, Integer value2) {
            addCriterion("menu_status between", value1, value2, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_status not between", value1, value2, "menuStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusIsNull() {
            addCriterion("menu_show_status is null");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusIsNotNull() {
            addCriterion("menu_show_status is not null");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusEqualTo(Integer value) {
            addCriterion("menu_show_status =", value, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusNotEqualTo(Integer value) {
            addCriterion("menu_show_status <>", value, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusGreaterThan(Integer value) {
            addCriterion("menu_show_status >", value, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_show_status >=", value, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusLessThan(Integer value) {
            addCriterion("menu_show_status <", value, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusLessThanOrEqualTo(Integer value) {
            addCriterion("menu_show_status <=", value, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusIn(List<Integer> values) {
            addCriterion("menu_show_status in", values, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusNotIn(List<Integer> values) {
            addCriterion("menu_show_status not in", values, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusBetween(Integer value1, Integer value2) {
            addCriterion("menu_show_status between", value1, value2, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andMenuShowStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_show_status not between", value1, value2, "menuShowStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeIsNull() {
            addCriterion("delete_type is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeIsNotNull() {
            addCriterion("delete_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeEqualTo(Integer value) {
            addCriterion("delete_type =", value, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeNotEqualTo(Integer value) {
            addCriterion("delete_type <>", value, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeGreaterThan(Integer value) {
            addCriterion("delete_type >", value, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_type >=", value, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeLessThan(Integer value) {
            addCriterion("delete_type <", value, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeLessThanOrEqualTo(Integer value) {
            addCriterion("delete_type <=", value, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeIn(List<Integer> values) {
            addCriterion("delete_type in", values, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeNotIn(List<Integer> values) {
            addCriterion("delete_type not in", values, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeBetween(Integer value1, Integer value2) {
            addCriterion("delete_type between", value1, value2, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_type not between", value1, value2, "deleteType");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNull() {
            addCriterion("delete_time is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNotNull() {
            addCriterion("delete_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeEqualTo(Date value) {
            addCriterion("delete_time =", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotEqualTo(Date value) {
            addCriterion("delete_time <>", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThan(Date value) {
            addCriterion("delete_time >", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_time >=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThan(Date value) {
            addCriterion("delete_time <", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("delete_time <=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIn(List<Date> values) {
            addCriterion("delete_time in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotIn(List<Date> values) {
            addCriterion("delete_time not in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeBetween(Date value1, Date value2) {
            addCriterion("delete_time between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("delete_time not between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}