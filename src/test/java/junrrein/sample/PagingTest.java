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
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PagingTest extends AbstractSampleTest {
    
    @Test
    public void pagingTest() {
        transactional(em -> {

            CriteriaBuilder<Organization> cb = cbf.create(em, Organization.class)
                .from(Organization.class, "org")
                .innerJoin("org.organizationPaymentMethods", "opm")
                .where("opm.id.paymentMethodId").eq(2)
                .orderByAsc("org.id");

            PaginatedCriteriaBuilder<Organization> pagedCb = cb.page(0, 100);

            System.out.println("\n====================================");
            List<Organization> list = cb.getResultList();
            System.out.println("Result list:");
            System.out.println(list);
            System.out.println("====================================");


            System.out.println("\n====================================");
            PagedList<Organization> pagedList = pagedCb.getResultList();
            System.out.println("\nResult list (paging):");
            System.out.println(pagedList);
            System.out.println("====================================");

            Assert.assertEquals(1, list.size());
            Assert.assertEquals(1, pagedList.size());
        });
    }
}
