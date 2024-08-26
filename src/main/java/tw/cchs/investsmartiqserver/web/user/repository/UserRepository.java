package tw.cchs.investsmartiqserver.web.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.cchs.investsmartiqserver.web.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUserId(Integer userId);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public long count();

}
