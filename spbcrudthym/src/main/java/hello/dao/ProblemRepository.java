package hello.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hello.entity.Problem;
import hello.entity.Product;

@Repository
public interface ProblemRepository extends PagingAndSortingRepository<Problem, Integer> {
	
	List<Problem> findByPersonAndIsResolvedOrderByProblemDateTimeDesc(String person, Boolean isResolved);
	List<Problem> findByPersonAndProductAndIsResolvedOrderByProblemDateTimeDesc(String person, Product product, Boolean isResolved);
	List<Problem> findByProductAndIsResolvedOrderByProblemDateTimeDesc(Product product, Boolean isResolved);
	List<Problem> findByProductOrderByProblemDateTimeDesc(Product product);
	List<Problem> findByPersonOrderByProblemDateTimeDesc(String person);
	
}
