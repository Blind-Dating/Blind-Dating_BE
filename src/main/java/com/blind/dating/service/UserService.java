package com.blind.dating.service;

import com.blind.dating.domain.UserAccount;
import com.blind.dating.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserAccountRepository userAccountRepository;

    public Page<UserAccount> getUserList(int score, String gender, Pageable pageable, String step){

        int number = Integer.parseInt(step);

        return userAccountRepository.findAllByScoreBetweenAndGender(score-(5*number), score+(5*number), gender,pageable);

    }

    public UserAccount getMyInfo(String userId){
        return userAccountRepository.findByUserId(userId);
    }
}
