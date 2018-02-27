package com.web.persistent;

import com.web.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alex on 13.02.18.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
