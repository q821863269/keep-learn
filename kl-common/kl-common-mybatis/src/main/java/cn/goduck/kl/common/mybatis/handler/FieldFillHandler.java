package cn.goduck.kl.common.mybatis.handler;

import cn.goduck.kl.common.core.base.BaseEntity;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.util.JwtUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/17 14:48
 */
public class FieldFillHandler implements MetaObjectHandler {

    /**
     * @see BaseEntity
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = getCurrentUserId();
        this.strictInsertFill(metaObject, GlobalConstant.CREATE_BY, Long.class, userId);
        this.strictInsertFill(metaObject, GlobalConstant.CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, GlobalConstant.UPDATE_BY, Long.class, userId);
        this.strictInsertFill(metaObject, GlobalConstant.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * @see BaseEntity
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = getCurrentUserId();
        this.strictInsertFill(metaObject, GlobalConstant.UPDATE_BY, Long.class, userId);
        this.strictInsertFill(metaObject, GlobalConstant.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 获取当前操作的用户Id
     */
    private Long getCurrentUserId() {
        return JwtUtil.getUserId();
    }

}