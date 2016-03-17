package interfaces;

import entity.Company;
import exception.CompanyNotFoundException;
import java.util.List;

public interface ICompanyFacade {

    public Company addCompany(Company c);

    public Company deleteCompany(int id);

    public Company getCompany(int id) throws CompanyNotFoundException;

    public List<Company> getCompanies();

    public Company editCompany(Company c);
}
