package interfaces;

import entity.Company;
import java.util.List;

public interface ICompanyFacade {

    public Company addCompany(Company c);

    public Company deleteCompany(int id);

    public Company getCompany(int id);

    public List<Company> getCompanys();

    public Company editCompany(Company c);
}
