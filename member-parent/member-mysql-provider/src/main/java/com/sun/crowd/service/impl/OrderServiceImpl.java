package com.sun.crowd.service.impl;

import com.sun.crowd.entity.po.AddressPO;
import com.sun.crowd.entity.po.AddressPOExample;
import com.sun.crowd.entity.po.OrderPO;
import com.sun.crowd.entity.po.OrderProjectPO;
import com.sun.crowd.entity.vo.AddressVO;
import com.sun.crowd.entity.vo.OrderProjectVO;
import com.sun.crowd.entity.vo.OrderVO;
import com.sun.crowd.mapper.AddressPOMapper;
import com.sun.crowd.mapper.OrderPOMapper;
import com.sun.crowd.mapper.OrderProjectPOMapper;
import com.sun.crowd.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderProjectPOMapper orderProjectPOMapper;

    @Resource
    private OrderPOMapper orderPOMapper;

    @Resource
    private AddressPOMapper addressPOMapper;

    @Override
    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getAddressVOList(Integer memberId) {

        AddressPOExample example = new AddressPOExample();

        example.createCriteria().andMemberIdEqualTo(memberId);

        List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);

        List<AddressVO> addressVOList = new ArrayList<AddressVO>();

        for (AddressPO addressPO : addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO, addressVO);

            addressVOList.add(addressVO);
        }

        return addressVOList;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveAddress(AddressVO addressVO) {

        AddressPO addressPO = new AddressPO();

        BeanUtils.copyProperties(addressVO, addressPO);

        addressPOMapper.insert(addressPO);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {

        OrderPO orderPO = new OrderPO();

        BeanUtils.copyProperties(orderVO, orderPO);

        OrderProjectPO orderProjectPO = new OrderProjectPO();

        BeanUtils.copyProperties(orderVO.getOrderProjectVO(), orderProjectPO);

        // 保存orderPO自动生成的主键是orderProjectPO需要用到的外键
        orderPOMapper.insert(orderPO);

        // 从orderPO中获取orderId
        Integer id = orderPO.getId();

        // 将orderId设置到orderProjectPO
        orderProjectPO.setOrderId(id);

        orderProjectPOMapper.insert(orderProjectPO);
    }
}
