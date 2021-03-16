package inventory.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceDAOImpl<Invoice> extends BaseDAOImpl<Invoice> implements InvoiceDAO<Invoice>{

}
