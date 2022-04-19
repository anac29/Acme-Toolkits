package acme.features.patron.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport>{

    @Autowired
    protected PatronPatronageReportRepository repo;
    
    @Override
    public boolean authorise(final Request<PatronageReport> request) {
        assert request != null;
        
        boolean res;
        int patronageReportId;
        PatronageReport patronageReport;
        
        patronageReportId = request.getModel().getInteger("id");
        patronageReport = this.repo.findOnePatronageReportById(patronageReportId);
        res = patronageReport != null && patronageReport.getPatronage().getPatron().getId() == request.getPrincipal().getActiveRoleId();
        
        
        return res;
    }

    @Override
    public PatronageReport findOne(final Request<PatronageReport> request) {
        PatronageReport res;
        int id;
        
        id = request.getModel().getInteger("id");
        res = this.repo.findOnePatronageReportById(id);
        
        return res;
    }

    @Override
    public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        
        request.unbind(entity, model, "automaticSequenceNumber", "creationMoment", "memorandum", "link", "patronage.code");
        model.setAttribute("masterId", entity.getPatronage().getInventor().getId());
    }

}