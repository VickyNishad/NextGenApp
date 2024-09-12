package com.nextgen.sms.repository;

import com.nextgen.sms.models.EmailSMSDetails;
import jakarta.ws.rs.QueryParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailSMSDetailsRepository extends JpaRepository<EmailSMSDetails,Long> {
    List<EmailSMSDetails> findByMobileNumber(@QueryParam("MobileNumber") String mobileNumber);
}
