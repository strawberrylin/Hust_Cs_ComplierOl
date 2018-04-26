package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午3:34 18-4-25
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer>{

}
