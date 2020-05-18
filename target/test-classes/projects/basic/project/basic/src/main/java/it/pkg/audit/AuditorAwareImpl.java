package it.pkg.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import it.pkg.security.util.LoggedInUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.of(0L);
		}
		Object principal = authentication.getPrincipal();
		LoggedInUser loggedInUser = null;
		String username = null;
		if (principal instanceof UserDetails) {
			loggedInUser = ((LoggedInUser) principal);
			username = loggedInUser.getUsername();
		} else {
			username = principal.toString();
		}
		log.debug("Current UserId::::" + loggedInUser.getUserId());
		log.debug("CurrentAuditor :: {}", username);
		return Optional.of(loggedInUser.getUserId());
	}

}
//https://www.javaguides.net/2018/09/spring-data-jpa-auditing-with-spring-boot2-and-mysql-example.html
//javarevisited.blogspot.com/2018/02/what-is-securitycontext-and-SecurityContextHolder-Spring-security.html#ixzz6KkM480dE
/*
 * 
 * 2020-05-02 20:03:07.540 ERROR 11888 --- [http-nio-8080-exec-2]
 * o.a.c.c.C.[.[.[.[dispatcherServlet] : Servlet.service() for servlet
 * [dispatcherServlet] in context with path [/api/dev] threw exception [Request
 * processing failed; nested exception is
 * org.springframework.transaction.TransactionSystemException: Could not commit
 * JPA transaction; nested exception is javax.persistence.RollbackException:
 * Error while committing the transaction] with root cause
 * 
 * java.lang.StackOverflowError: null
 * 
 * The reason for the behavior you see is that the AuditorAware implementation
 * is called from within a JPA @PrePersist/@PreUpdate callback. You now issue a
 * query by calling findByEmail(…), which triggers the dirty-detection again,
 * which in turn causes the flushing to be triggered and thus the invocation of
 * the callbacks.
 * 
 * The recommended workaround is to keep an instance of the UserModel inside the
 * Spring Security User implementation (by looking it up when the
 * UserDetailsService looks up the instance on authentication), so that you
 * don't need an extra database query.
 * 
 * Another (less recommended) workaround could be to inject an EntityManager
 * into the AuditorAware implementation, call setFlushMode(FlushModeType.COMMIT)
 * before the query execution and reset it to FlushModeType.AUTO after that, so
 * that the flush will not be triggered for the query execution.
 */