package com146.HOME.CA.BE.domain.login.svc;

import com146.HOME.CA.BE.domain.login.dao.LoginDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginSVCImpl implements LoginSVC{

  private final LoginDAO loginDAO;

  @Override
  public String searchTelID(String name, String tel) {
    return null;
  }

  @Override
  public String searchEmailID(String name, String email) {
    return null;
  }

  @Override
  public String searchTelPW(String id, String tel) {
    return null;
  }

  @Override
  public String searchEmailPW(String id, String email) {
    return null;
  }
}