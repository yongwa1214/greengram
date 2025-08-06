package kr.co.wikibook.greengram2.application.user;

import kr.co.wikibook.greengram2.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
