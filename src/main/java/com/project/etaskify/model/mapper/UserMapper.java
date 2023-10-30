package com.project.etaskify.model.mapper;

import com.project.etaskify.model.dto.request.RegisterRequest;
import com.project.etaskify.model.dto.response.UserResponse;
import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.entity.UserTemp;
import com.project.etaskify.util.Constant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = PasswordEncoder.class)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "otpCode", source = "confirmPassword", qualifiedByName = "generateOtpCode") //source is not using
    @Mapping(target = "userRole", constant = "1")
    public abstract UserTemp toUserTempAdmin(RegisterRequest request);

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "userRole", constant = "2")
    public abstract User toUser(RegisterRequest request);

    public abstract User toUser(UserTemp userTemp);

    @Mapping(target = "userRole", source = "userRoleAsEnum")
    public abstract UserResponse toUserResponse(User user);

    public abstract List<UserResponse> toUserResponses(List<User> users);

    @Named("encodePassword")
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Named("generateOtpCode")
    public String generateOtpCode(String confirmPassword) {
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        char[] otp = new char[Constant.OTP_CODE_LENGTH];

        for (int i = 0; i < Constant.OTP_CODE_LENGTH; i++)
        {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return String.valueOf(otp);
    }

    @Autowired
    protected void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
