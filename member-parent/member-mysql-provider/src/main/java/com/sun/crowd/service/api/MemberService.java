package com.sun.crowd.service.api;

import com.sun.crowd.entity.po.MemberPO;

public interface MemberService {

    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMember(MemberPO memberPO);
}
