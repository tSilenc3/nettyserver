package com.adward.netty.netty.logic.user;

import com.adward.netty.entity.User;
import com.adward.netty.netty.logic.Common.ResponseData;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.adward.netty.netty.net.SessionCloseReason;
import com.adward.netty.netty.utils.SessionUtil;
import com.adward.netty.respository.UserRepository;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(Channel channel, String userName, String password) {
        IoSession session = SessionUtil.getSessionByChannel(channel);
        User user = userRepository.findByUserName(userName);

        if (user == null || !user.getPassword().equals(password)) {
            ResponseData responseData = new ResponseData(PacketType.login.getType(), "账号不存在或密码错误");
            session.sendPacket(responseData);
        } else {
            SessionUtil.addChannelUser(channel, user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", user.getUserName());
            jsonObject.put("id", user.getId());
            ResponseData responseData = new ResponseData(PacketType.login.getType(), jsonObject);
            session.sendPacket(responseData);
        }
    }

    public void registerNewUser(Channel channel, String userName, String password) {
        IoSession ioSession = SessionUtil.getSessionByChannel(channel);
        User user = userRepository.findByUserName(userName);

        if (user != null) {
            ResponseData responseData = new ResponseData(PacketType.register.getType(), "账号已存在");
            ioSession.sendPacket(responseData);
        } else {
            User newUser = createNewUser(userName, password);

            ResponseData responseData = new ResponseData(PacketType.register.getType(), newUser.getId());

            ioSession.sendPacket(responseData);
        }
    }

    private User createNewUser(String userName, String password) {
        User user = new User(userName, password);

        userRepository.save(user);

        return user;
    }

    public void userLogout(Channel channel, SessionCloseReason closeReason) {
        IoSession session = SessionUtil.getSessionByChannel(channel);

        long userId = SessionUtil.getUserByChannel(channel).getId();

    }
}
