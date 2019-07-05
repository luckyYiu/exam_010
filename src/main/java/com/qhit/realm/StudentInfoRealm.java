package com.qhit.realm;

import com.qhit.mapper.StudentInfoMapper;
import com.qhit.pojo.StudentInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class StudentInfoRealm extends AuthenticatingRealm {

    @Autowired
    public StudentInfoMapper studentInfoMapper;

    public void setStudentInfoMapper(StudentInfoMapper studentInfoMapper) {
        this.studentInfoMapper = studentInfoMapper;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = ((UsernamePasswordToken)authenticationToken).getUsername();
        StudentInfo student = studentInfoMapper.getStudentByName(username);
        if(student==null){
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes(student.getSalt());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(student,student.getStudentPwd(),salt,getName());
        return simpleAuthenticationInfo;
    }


}
