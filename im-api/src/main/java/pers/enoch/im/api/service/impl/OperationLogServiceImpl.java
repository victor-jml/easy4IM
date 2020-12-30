package pers.enoch.im.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.OperationLogMapper;
import pers.enoch.im.api.model.OperationLog;
import pers.enoch.im.api.service.OperationLogService;

/**
 * @Author yang.zhao
 * Date: 2020/12/24
 * Description: 操作日志 服务类
 **/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
}
