package com.example.sys.service.impl;

import com.example.sys.entity.Loan;
import com.example.sys.mapper.LoanMapper;
import com.example.sys.service.ILoanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-04-04
 */
@Service
public class LoanServiceImpl extends ServiceImpl<LoanMapper, Loan> implements ILoanService {

}
