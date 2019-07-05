package com.qhit.realm;

import com.qhit.mapper.TeacherInfoMapper;
import com.qhit.pojo.TeacherInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherInfoRealm extends AuthenticatingRealm {

    @Autowired
    public TeacherInfoMapper teacherInfoMapper;

    public void setTeacherInfoMapper(TeacherInfoMapper teacherInfoMapper) {
        this.teacherInfoMapper = teacherInfoMapper;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = ((UsernamePasswordToken)authenticationToken).getUsername();
        TeacherInfo teacher = teacherInfoMapper.getTeacherByName(username);
        if(teacher==null){
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes(teacher.getSalt());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(teacher,teacher.getTeacherPwd(),salt,getName());
        return simpleAuthenticationInfo;
    }
    
}
