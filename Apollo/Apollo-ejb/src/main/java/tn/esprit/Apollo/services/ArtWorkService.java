package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import tn.esprit.Apollo.Facade.SearchCriteria;
import tn.esprit.Apollo.persistence.ArtWork;

@Stateless
@LocalBean
public class ArtWorkService extends AbstractFacade<ArtWork> implements ArtWorkServiceRemote {

	public ArtWorkService() {
		super(ArtWork.class);
		// TODO Auto-generated constructor stub
	}
//search by paginateur of 10 
	public List<ArtWork> paginateur(int id) {

		int pageNumber = id;
		int pageSize = 10;
		if (pageNumber != 1) {
			pageNumber = (id - 1) * pageSize;
		}
		//create criteria builder from entity manager
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        //create query for to get the number of object instance in the database
		CriteriaQuery<Long> countQ = criteriaBuilder.createQuery(Long.class);
		countQ.select(criteriaBuilder.count(countQ.from(ArtWork.class)));
		Long count = em.createQuery(countQ).getSingleResult();
         //create criteriaquery select from the object class 
		CriteriaQuery<ArtWork> criteriaQuery = criteriaBuilder.createQuery(ArtWork.class);
		Root<ArtWork> from = criteriaQuery.from(ArtWork.class);
		CriteriaQuery<ArtWork> select = criteriaQuery.select(from);
          //critera  with condition
		TypedQuery<ArtWork> typedQuery = em.createQuery(select);
		while (pageNumber < count.intValue()) {
			typedQuery.setFirstResult(pageNumber - 1);
			typedQuery.setMaxResults(pageSize);
			System.out.println("Current page: " + id);
			pageNumber += pageSize;
		}
		List<ArtWork> listART = typedQuery.getResultList();
		System.out.println(pageNumber);
		return listART;
	}
//search par criteria i wish i was working with spring
	public List<ArtWork> searchArtWork(List<SearchCriteria> params) {
		//criteriabuilder  creation 
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ArtWork> query = builder.createQuery(ArtWork.class);
		Root<ArtWork> r = query.from(ArtWork.class);
		//create predicate object
		Predicate predicate = builder.conjunction();
		//get searchCriteria class to create condition for our search query
		for (SearchCriteria param : params) {
			if (param.getOperation().equalsIgnoreCase(">")) {
				predicate = builder.and(predicate,
						builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
			} else if (param.getOperation().equalsIgnoreCase("<")) {
				predicate = builder.and(predicate,
						builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
			} else if (param.getOperation().equalsIgnoreCase(":")) {
				if (r.get(param.getKey()).getJavaType() == String.class) {
					predicate = builder.and(predicate,
							builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
				} else {
					predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
				}
			}
		}
		query.where(predicate);
        //create our query and do the search
		List<ArtWork> result = em.createQuery(query).getResultList();
		return result;
	}

	
	
	
	
}
