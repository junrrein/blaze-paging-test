/*
 * Copyright 2014 - 2023 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package junrrein.sample;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.PaginatedCriteriaBuilder;
import junrrein.model.Organization;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PagingTest extends AbstractSampleTest {
    
    @Test
    public void pagingTest() {
        transactional(em -> {

            String jpaQueryString = """
                SELECT org FROM Organization org
                JOIN org.paymentMethods pm
                WHERE pm.name = 'Debit card'
                ORDER BY org.id
            """;
            TypedQuery<Organization> jpaQuery = em.createQuery(jpaQueryString, Organization.class);

            TypedQuery<Organization> pagedJpaQuery = em.createQuery(jpaQueryString, Organization.class)
                .setFirstResult(0)
                .setMaxResults(100);

            CriteriaBuilder<Organization> cb = cbf.create(em, Organization.class)
                .from(Organization.class, "org")
                .innerJoin("org.paymentMethods", "pm")
                .where("pm.name").eq("Debit card")
                .orderByAsc("org.id");

            PaginatedCriteriaBuilder<Organization> pagedCb = cb.page(0, 100);

            System.out.println("\n========= Using plain JPA ===========================");
            List<Organization> jpaList = jpaQuery.getResultList();
            System.out.println("Result list (Plain JPA):");
            System.out.println(jpaList);
            System.out.println("====================================");

            System.out.println("\n========= Using plain JPA (paging) ===========================");
            List<Organization> pagedJpaList = pagedJpaQuery.getResultList();
            System.out.println("Result list (Plain JPA) (paging):");
            System.out.println(pagedJpaList);
            System.out.println("====================================");

            System.out.println("\n========= Using Blaze ===========================");
            List<Organization> blazeList = cb.getResultList();
            System.out.println("Result list (Blaze):");
            System.out.println(blazeList);
            System.out.println("====================================");

            System.out.println("\n========= Using Blaze (paging) ===========================");
            PagedList<Organization> blazePagedList = pagedCb.getResultList();
            System.out.println("\nResult list (Blaze) (paging):");
            System.out.println(blazePagedList);
            System.out.println("====================================");

            assertEquals(1, jpaList.size());
            assertEquals(1, pagedJpaList.size());
            assertEquals(1, blazeList.size());
            assertEquals(1, blazePagedList.size());
        });
    }
}
