package com.sun.crowd.test;

import com.sun.crowd.entity.po.MemberPO;
import com.sun.crowd.entity.vo.DetailProjectVO;
import com.sun.crowd.entity.vo.DetailReturnVO;
import com.sun.crowd.entity.vo.PortalProjectVO;
import com.sun.crowd.entity.vo.PortalTypeVO;
import com.sun.crowd.mapper.MemberPOMapper;
import com.sun.crowd.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Resource
    private DataSource dataSource;

    @Resource
    private MemberPOMapper memberPOMapper;

    @Resource
    private ProjectPOMapper projectPOMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testLoadDetailProjectVO() {

        Integer projectId = 2;

        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);

        logger.info(detailProjectVO.getProjectId() + "");
        logger.info(detailProjectVO.getProjectName());
        logger.info(detailProjectVO.getProjectDesc());
        logger.info(detailProjectVO.getFollowerCount() + "");
        logger.info(detailProjectVO.getStatus() + "");
        logger.info(detailProjectVO.getMoney() + "");
        logger.info(detailProjectVO.getSupportMoney() + "");
        logger.info(detailProjectVO.getPercentage() + "");
        logger.info(detailProjectVO.getDeployDate() + "");
        logger.info(detailProjectVO.getSupporterCount() + "");
        logger.info(detailProjectVO.getHeaderPicturePath());

        List<String> detailPicturePathList = detailProjectVO.getDetailPicturePathList();
        for (String path : detailPicturePathList) {
            logger.info("detail path="+path);
        }

        List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();
        for (DetailReturnVO detailReturnVO : detailReturnVOList) {
            logger.info(detailReturnVO.getReturnId() + "");
            logger.info(detailReturnVO.getSupportMoney() + "");
            logger.info(detailReturnVO.getSignalPurchase() + "");
            logger.info(detailReturnVO.getPurchase() + "");
            logger.info(detailReturnVO.getSupporterCount() + "");
            logger.info(detailReturnVO.getFreight() + "");
            logger.info(detailReturnVO.getReturnDate() + "");
            logger.info(detailReturnVO.getContent() + "");
            logger.info(detailReturnVO.getFreight() + "");
        }
    }

    @Test
    public void testMapper(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String source = "123123";

        String encode = passwordEncoder.encode(source);

        MemberPO memberPO = new MemberPO(null, "jack", encode, "杰克", "jack@qq.com", 1, 1, "杰克", "123123", 2);

        memberPOMapper.insert(memberPO);
    }

    @Test
    public void testConnection() throws SQLException {

        Connection connection = dataSource.getConnection();

        logger.debug(connection.toString());
    }

    @Test
    public void testLoadTypeData(){

        List<PortalTypeVO> typeVOList = projectPOMapper.selectPortalTypeVOList();

        for (PortalTypeVO portalTypeVO : typeVOList) {
            String name = portalTypeVO.getName();
            String remark = portalTypeVO.getRemark();
            logger.info("name="+name+" remark="+remark);

            List<PortalProjectVO> projectVOList = portalTypeVO.getPortalProjectVOList();
            for (PortalProjectVO portalProjectVO : projectVOList) {

                if(portalProjectVO == null) {
                    continue;
                }

                logger.info(portalProjectVO.toString());
            }
        }
    }
}
