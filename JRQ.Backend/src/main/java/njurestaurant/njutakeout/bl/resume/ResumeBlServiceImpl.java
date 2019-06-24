package njurestaurant.njutakeout.bl.resume;

import njurestaurant.njutakeout.blservice.resume.ResumeBlService;
import njurestaurant.njutakeout.dataservice.resume.ResumeDataService;
import njurestaurant.njutakeout.dataservice.resume.ResumeEducationDataService;
import njurestaurant.njutakeout.dataservice.resume.ResumeInternShipDataService;
import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.entity.resume.ResumeEducation;
import njurestaurant.njutakeout.entity.resume.ResumeInternShip;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.resume.ResumeItem;
import njurestaurant.njutakeout.response.resume.ResumeListResponce;
import njurestaurant.njutakeout.response.resume.ResumeResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ResumeBlServiceImpl implements ResumeBlService {

    private  final ResumeDataService resumeDataService;
    private  final ResumeEducationDataService resumeEducationDataService;
    private  final ResumeInternShipDataService resumeInternShipDataService;


    @Autowired
    public ResumeBlServiceImpl(ResumeDataService resumeDataService, ResumeEducationDataService resumeEducationDataService, ResumeInternShipDataService resumeInternShipDataService) {
        this.resumeDataService = resumeDataService;
        this.resumeEducationDataService = resumeEducationDataService;
        this.resumeInternShipDataService = resumeInternShipDataService;
    }

    @Override
    public InfoResponse add(Resume resume) throws NotExistException {
        resumeDataService.add(resume);
        for(ResumeEducation i:resume.getResumeEducation()){
            i.setResumeId(resume.getResumeId());
            resumeEducationDataService.add(i);

        }
        for(ResumeInternShip j:resume.getResumeInternShips()){
            j.setResumeId(resume.getResumeId());
            resumeInternShipDataService.add(j);
        }
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(Resume resume) throws NotExistException {
        resumeDataService.update(resume);
        for(ResumeEducation i:resume.getResumeEducation())
            resumeEducationDataService.update(i);
        for(ResumeInternShip j:resume.getResumeInternShips())
            resumeInternShipDataService.update(j);
        return new InfoResponse();
    }

    @Override
    public InfoResponse deleteById(String resumeId) throws NotExistException {
        resumeDataService.deleteById(resumeId);
        return new InfoResponse();
    }

    @Override
    public ResumeResponce findById(String id) throws NotExistException {
        Resume r=resumeDataService.findById(id);
        r.setResumeEducation(resumeEducationDataService.getByResumeId(id));
        r.setResumeInternShips(resumeInternShipDataService
                .getByResumeId(id));

        return new ResumeResponce(new ResumeItem(r));
    }

    @Override
    public ResumeListResponce getAll() {
        List<Resume>list=resumeDataService.getAll();
        for (Resume i:list)
           i.setResumeInternShips(resumeInternShipDataService.getByResumeId(i.getResumeId()));
        List<ResumeItem> re=new ArrayList<>();
        if (list!= null && list.size() > 0){
            for (Resume i:list)
                re.add(new ResumeItem(i));
        }
        return new ResumeListResponce(re);
    }
    @Override
    public ResumeListResponce findByUserId(String userId) {
        List<Resume>list=resumeDataService.getByUserId(userId);
        for(int i=0;i<list.size();i++){
            list.get(i).setResumeInternShips(resumeInternShipDataService.getByResumeId(list.get(i).getResumeId()));
        }
        List<ResumeItem> re=new ArrayList<>();
        if (list!= null && list.size() > 0){
            for (Resume i:list)
                re.add(new ResumeItem(i));
        }
        return new ResumeListResponce(re);
    }
}
