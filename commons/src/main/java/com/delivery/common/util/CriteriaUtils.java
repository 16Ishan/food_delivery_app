package com.delivery.common.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CriteriaUtils {

	/**
	 * Returns {@link Page} of results for a {@link CriteriaQuery} and {@link Pageable} object.
	 *
	 * @param entityManager {@link EntityManager}
	 * @param criteria      {@link CriteriaQuery}
	 * @param root          {@link Root}
	 * @param pageable      {@link Pageable}
	 * @return Page
	 */
	public static <Q, R> Page<Q> getResultsPage(final EntityManager entityManager, final CriteriaQuery<Q> criteria,
			final Root<R> root, final Pageable pageable, final List<Order> defaultOrderList,
			final List<String> countFields) {
		return PageableExecutionUtils.getPage(getResultList(entityManager, criteria, root, pageable, defaultOrderList), pageable, () -> JpaUtils.count(entityManager, criteria, countFields));
	}

	/**
	 * Returns {@link List} of results for a {@link CriteriaQuery} and {@link Pageable} object.
	 *
	 * @param entityManager {@link EntityManager}
	 * @param criteria      {@link CriteriaQuery}
	 * @param root          {@link Root}
	 * @param pageable      {@link Pageable}
	 * @return List
	 */
	public static <Q, R> List<Q> getResultList(final EntityManager entityManager, final CriteriaQuery<Q> criteria, final Root<R> root, final Pageable pageable,
			final List<Order> defaultOrderList) {
		setOrderBy(entityManager, criteria, root, pageable, defaultOrderList);
		TypedQuery<Q> resultQuery = entityManager.createQuery(criteria);
		if (Objects.nonNull(pageable) && pageable.isPaged()) {
			resultQuery.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
		}
		return resultQuery.getResultList();
	}

	private static <Q, R> void setOrderBy(final EntityManager em, final CriteriaQuery<Q> criteria, final Root<R> root, final Pageable pageable,
			final List<Order> defaultOrderList) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Order> sortOrderList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(criteria.getOrderList())) {
			return;
		}
		if (Objects.nonNull(pageable) && pageable.getSort().isSorted()) {
			sortOrderList.addAll(QueryUtils.toOrders(pageable.getSort(), root, cb));
		}
		if (Objects.nonNull(defaultOrderList) && CollectionUtils.isNotEmpty(defaultOrderList)) {
			sortOrderList.addAll(defaultOrderList);
		}
		criteria.orderBy(sortOrderList);
	}

	/**
	 * Return join of root with entity to be joined based on joinType
	 *
	 * @param root     {@link Root}
	 * @param joinName {@link String}
	 * @param joinType {@link JoinType}
	 * @return Join
	 */
	@SuppressWarnings("unchecked")
	public static <Q, R> Join<R, Q> joinIfNotExists(final Root<R> root, final String joinName, final JoinType joinType) {
		return (Join<R, Q>) root.getJoins().stream().filter(join -> StringUtils.equals(join.getAttribute().getName(), joinName) && join.getJoinType().equals(joinType))
								.findFirst()
								.orElseGet(() -> root.join(joinName, joinType));
	}

}
