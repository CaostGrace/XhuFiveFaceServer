package cn.logcode.xhufiveface.dao.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaceUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FaceUserExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNickIsNull() {
            addCriterion("user_nick is null");
            return (Criteria) this;
        }

        public Criteria andUserNickIsNotNull() {
            addCriterion("user_nick is not null");
            return (Criteria) this;
        }

        public Criteria andUserNickEqualTo(String value) {
            addCriterion("user_nick =", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotEqualTo(String value) {
            addCriterion("user_nick <>", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickGreaterThan(String value) {
            addCriterion("user_nick >", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickGreaterThanOrEqualTo(String value) {
            addCriterion("user_nick >=", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickLessThan(String value) {
            addCriterion("user_nick <", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickLessThanOrEqualTo(String value) {
            addCriterion("user_nick <=", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickLike(String value) {
            addCriterion("user_nick like", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotLike(String value) {
            addCriterion("user_nick not like", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickIn(List<String> values) {
            addCriterion("user_nick in", values, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotIn(List<String> values) {
            addCriterion("user_nick not in", values, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickBetween(String value1, String value2) {
            addCriterion("user_nick between", value1, value2, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotBetween(String value1, String value2) {
            addCriterion("user_nick not between", value1, value2, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserRealIsNull() {
            addCriterion("user_real is null");
            return (Criteria) this;
        }

        public Criteria andUserRealIsNotNull() {
            addCriterion("user_real is not null");
            return (Criteria) this;
        }

        public Criteria andUserRealEqualTo(String value) {
            addCriterion("user_real =", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealNotEqualTo(String value) {
            addCriterion("user_real <>", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealGreaterThan(String value) {
            addCriterion("user_real >", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealGreaterThanOrEqualTo(String value) {
            addCriterion("user_real >=", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealLessThan(String value) {
            addCriterion("user_real <", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealLessThanOrEqualTo(String value) {
            addCriterion("user_real <=", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealLike(String value) {
            addCriterion("user_real like", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealNotLike(String value) {
            addCriterion("user_real not like", value, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealIn(List<String> values) {
            addCriterion("user_real in", values, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealNotIn(List<String> values) {
            addCriterion("user_real not in", values, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealBetween(String value1, String value2) {
            addCriterion("user_real between", value1, value2, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserRealNotBetween(String value1, String value2) {
            addCriterion("user_real not between", value1, value2, "userReal");
            return (Criteria) this;
        }

        public Criteria andUserSexIsNull() {
            addCriterion("user_sex is null");
            return (Criteria) this;
        }

        public Criteria andUserSexIsNotNull() {
            addCriterion("user_sex is not null");
            return (Criteria) this;
        }

        public Criteria andUserSexEqualTo(Integer value) {
            addCriterion("user_sex =", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotEqualTo(Integer value) {
            addCriterion("user_sex <>", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexGreaterThan(Integer value) {
            addCriterion("user_sex >", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_sex >=", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLessThan(Integer value) {
            addCriterion("user_sex <", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLessThanOrEqualTo(Integer value) {
            addCriterion("user_sex <=", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexIn(List<Integer> values) {
            addCriterion("user_sex in", values, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotIn(List<Integer> values) {
            addCriterion("user_sex not in", values, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexBetween(Integer value1, Integer value2) {
            addCriterion("user_sex between", value1, value2, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotBetween(Integer value1, Integer value2) {
            addCriterion("user_sex not between", value1, value2, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserHeadIsNull() {
            addCriterion("user_head is null");
            return (Criteria) this;
        }

        public Criteria andUserHeadIsNotNull() {
            addCriterion("user_head is not null");
            return (Criteria) this;
        }

        public Criteria andUserHeadEqualTo(String value) {
            addCriterion("user_head =", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotEqualTo(String value) {
            addCriterion("user_head <>", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadGreaterThan(String value) {
            addCriterion("user_head >", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadGreaterThanOrEqualTo(String value) {
            addCriterion("user_head >=", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadLessThan(String value) {
            addCriterion("user_head <", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadLessThanOrEqualTo(String value) {
            addCriterion("user_head <=", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadLike(String value) {
            addCriterion("user_head like", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotLike(String value) {
            addCriterion("user_head not like", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadIn(List<String> values) {
            addCriterion("user_head in", values, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotIn(List<String> values) {
            addCriterion("user_head not in", values, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadBetween(String value1, String value2) {
            addCriterion("user_head between", value1, value2, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotBetween(String value1, String value2) {
            addCriterion("user_head not between", value1, value2, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserBirIsNull() {
            addCriterion("user_bir is null");
            return (Criteria) this;
        }

        public Criteria andUserBirIsNotNull() {
            addCriterion("user_bir is not null");
            return (Criteria) this;
        }

        public Criteria andUserBirEqualTo(Date value) {
            addCriterion("user_bir =", value, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirNotEqualTo(Date value) {
            addCriterion("user_bir <>", value, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirGreaterThan(Date value) {
            addCriterion("user_bir >", value, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirGreaterThanOrEqualTo(Date value) {
            addCriterion("user_bir >=", value, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirLessThan(Date value) {
            addCriterion("user_bir <", value, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirLessThanOrEqualTo(Date value) {
            addCriterion("user_bir <=", value, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirIn(List<Date> values) {
            addCriterion("user_bir in", values, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirNotIn(List<Date> values) {
            addCriterion("user_bir not in", values, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirBetween(Date value1, Date value2) {
            addCriterion("user_bir between", value1, value2, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserBirNotBetween(Date value1, Date value2) {
            addCriterion("user_bir not between", value1, value2, "userBir");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("user_phone is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("user_phone =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("user_phone <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("user_phone >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("user_phone >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("user_phone <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("user_phone <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("user_phone like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("user_phone not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("user_phone in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("user_phone not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("user_phone between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("user_phone not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("user_email is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("user_email is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("user_email =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("user_email <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("user_email >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("user_email >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("user_email <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("user_email <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("user_email like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("user_email not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("user_email in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("user_email not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("user_email between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("user_email not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andLoginIpIsNull() {
            addCriterion("login_ip is null");
            return (Criteria) this;
        }

        public Criteria andLoginIpIsNotNull() {
            addCriterion("login_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLoginIpEqualTo(String value) {
            addCriterion("login_ip =", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotEqualTo(String value) {
            addCriterion("login_ip <>", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpGreaterThan(String value) {
            addCriterion("login_ip >", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("login_ip >=", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLessThan(String value) {
            addCriterion("login_ip <", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLessThanOrEqualTo(String value) {
            addCriterion("login_ip <=", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLike(String value) {
            addCriterion("login_ip like", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotLike(String value) {
            addCriterion("login_ip not like", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpIn(List<String> values) {
            addCriterion("login_ip in", values, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotIn(List<String> values) {
            addCriterion("login_ip not in", values, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpBetween(String value1, String value2) {
            addCriterion("login_ip between", value1, value2, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotBetween(String value1, String value2) {
            addCriterion("login_ip not between", value1, value2, "loginIp");
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

        public Criteria andUserPwdIsNull() {
            addCriterion("user_pwd is null");
            return (Criteria) this;
        }

        public Criteria andUserPwdIsNotNull() {
            addCriterion("user_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andUserPwdEqualTo(String value) {
            addCriterion("user_pwd =", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotEqualTo(String value) {
            addCriterion("user_pwd <>", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdGreaterThan(String value) {
            addCriterion("user_pwd >", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdGreaterThanOrEqualTo(String value) {
            addCriterion("user_pwd >=", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdLessThan(String value) {
            addCriterion("user_pwd <", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdLessThanOrEqualTo(String value) {
            addCriterion("user_pwd <=", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdLike(String value) {
            addCriterion("user_pwd like", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotLike(String value) {
            addCriterion("user_pwd not like", value, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdIn(List<String> values) {
            addCriterion("user_pwd in", values, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotIn(List<String> values) {
            addCriterion("user_pwd not in", values, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdBetween(String value1, String value2) {
            addCriterion("user_pwd between", value1, value2, "userPwd");
            return (Criteria) this;
        }

        public Criteria andUserPwdNotBetween(String value1, String value2) {
            addCriterion("user_pwd not between", value1, value2, "userPwd");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("access_token is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("access_token is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("access_token =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("access_token <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("access_token >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("access_token >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("access_token <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("access_token <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("access_token like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("access_token not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("access_token in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("access_token not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("access_token between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("access_token not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeIsNull() {
            addCriterion("expires_time is null");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeIsNotNull() {
            addCriterion("expires_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeEqualTo(Date value) {
            addCriterion("expires_time =", value, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeNotEqualTo(Date value) {
            addCriterion("expires_time <>", value, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeGreaterThan(Date value) {
            addCriterion("expires_time >", value, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expires_time >=", value, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeLessThan(Date value) {
            addCriterion("expires_time <", value, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeLessThanOrEqualTo(Date value) {
            addCriterion("expires_time <=", value, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeIn(List<Date> values) {
            addCriterion("expires_time in", values, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeNotIn(List<Date> values) {
            addCriterion("expires_time not in", values, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeBetween(Date value1, Date value2) {
            addCriterion("expires_time between", value1, value2, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andExpiresTimeNotBetween(Date value1, Date value2) {
            addCriterion("expires_time not between", value1, value2, "expiresTime");
            return (Criteria) this;
        }

        public Criteria andProhibitIsNull() {
            addCriterion("prohibit is null");
            return (Criteria) this;
        }

        public Criteria andProhibitIsNotNull() {
            addCriterion("prohibit is not null");
            return (Criteria) this;
        }

        public Criteria andProhibitEqualTo(Integer value) {
            addCriterion("prohibit =", value, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitNotEqualTo(Integer value) {
            addCriterion("prohibit <>", value, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitGreaterThan(Integer value) {
            addCriterion("prohibit >", value, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitGreaterThanOrEqualTo(Integer value) {
            addCriterion("prohibit >=", value, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitLessThan(Integer value) {
            addCriterion("prohibit <", value, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitLessThanOrEqualTo(Integer value) {
            addCriterion("prohibit <=", value, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitIn(List<Integer> values) {
            addCriterion("prohibit in", values, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitNotIn(List<Integer> values) {
            addCriterion("prohibit not in", values, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitBetween(Integer value1, Integer value2) {
            addCriterion("prohibit between", value1, value2, "prohibit");
            return (Criteria) this;
        }

        public Criteria andProhibitNotBetween(Integer value1, Integer value2) {
            addCriterion("prohibit not between", value1, value2, "prohibit");
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