package com.sun.crowd.service.api;

import com.sun.crowd.entity.vo.DetailProjectVO;
import com.sun.crowd.entity.vo.PortalTypeVO;
import com.sun.crowd.entity.vo.ProjectVO;

import java.util.List;

public interface ProjectService {

    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);
}
