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

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;

import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junrrein.model.*;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractSampleTest {
    
    protected EntityManagerFactory emf;
    protected CriteriaBuilderFactory cbf;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("default");
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        cbf = config.createCriteriaBuilderFactory(emf);
        
        transactional(em -> {

            Organization org = new Organization("UNICEF");
            PaymentMethod debit = new PaymentMethod("Debit card");
            PaymentMethod credit = new PaymentMethod("Credit card");

            em.persist(org);
            em.persist(debit);
            em.persist(credit);

            org.setPaymentMethods(List.of(debit, credit));
        });
    }
    
    protected void transactional(Consumer<EntityManager> consumer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean success = false;
        
        try {
            tx.begin();
            consumer.accept(em);
            success = true;
        } finally {
            try {
                if (success) {
                    tx.commit();
                } else {
                    tx.rollback();
                }
            } finally {
                em.close();
            }
        }
    }

    @After
    public void destruct() {
        emf.close();
    }
}
