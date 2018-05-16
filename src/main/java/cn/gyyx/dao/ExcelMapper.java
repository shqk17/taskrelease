package cn.gyyx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.gyyx.bean.ExcelDataBean;
@Repository
public interface ExcelMapper extends JpaRepository<ExcelDataBean,String>{

	
}
