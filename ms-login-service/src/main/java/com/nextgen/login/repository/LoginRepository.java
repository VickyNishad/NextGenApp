package com.nextgen.login.repository;

import com.nextgen.login.model.LoginDetails;
import jakarta.ws.rs.QueryParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails,Long> {
    public List<LoginDetails> findByMobileNumber(@QueryParam("MobileNumber") String mobileNumber);
}
