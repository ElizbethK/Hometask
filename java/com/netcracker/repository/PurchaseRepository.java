package com.netcracker.repository;


import com.netcracker.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Purchase findById(int id);

    //ex. 2(c)
    @Query(value = "select distinct prch_date from purchase", nativeQuery = true)
    List<Date> getDates();

    //ex. 4(a)
    @Query(value ="select c.cust_surname, s.sh_name from purchase p "
            + "join customers c on (p.cust_id = c.cust_id) join shop s on (p.shop_id = s.sh_id)", nativeQuery = true)
     List<Object> findCustSurnAndShNameForPurchases();

    //ex. 4(b)
    @Query(value = "select p.prch_date, c.cust_surname, c.cust_discount, bks.book_name, p.prch_quantity\n" +
                    "from purchase p join books bks on (p.book_id = bks.book_id) \n" +
                    "    join customers c on (p.cust_id = c.cust_id)", nativeQuery = true)
    List<Object> getDateSurnmDiscountNameQuantity();


    //ex. 5(a)
    @Query(value = "select p.prch_id, c.cust_surname, p.prch_date\n" +
            "from purchase p JOIN customers c ON (c.cust_id = p.cust_id)\n" +
            "where p.prch_amount >= :amount", nativeQuery = true)
    List<Object> getPurchIdDateCustSurnameWhereAmountMoreThanValue(double amount);

    //ex. 5(b)

    @Query(value = "select p.prch_date as purchaseDate, c.cust_surname as customerSurname, c.cust_district as customerDistrict" +
    "from purchase p join customers c on (p.cust_id = c.cust_id)" +
    "join shop s on (p.shop_id = s.sh_id)" +
    "where p.prch_date > :date and c.cust_district = s.sh_district order by p.prch_date", nativeQuery = true)
            List<Object> getPurchByDateAndDistinct(Date date);


}