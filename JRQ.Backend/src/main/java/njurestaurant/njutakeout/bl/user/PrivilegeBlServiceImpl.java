package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.PrivilegeBlService;
import njurestaurant.njutakeout.dataservice.user.PrivilegeDataService;
import njurestaurant.njutakeout.entity.user.Privilege;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.user.PrivilegeItem;
import njurestaurant.njutakeout.response.user.PrivilegeListResponse;
import njurestaurant.njutakeout.response.user.PrivilegeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeBlServiceImpl implements PrivilegeBlService {
	private final PrivilegeDataService privilegeDataService;

	@Autowired
	public PrivilegeBlServiceImpl(PrivilegeDataService privilegeDataService) {
		this.privilegeDataService = privilegeDataService;
	}

	@Override
	public BoolResponse addPrivilege(String name, int price) {
		privilegeDataService.addPrivilege(new Privilege(name, price));
		return new BoolResponse(true, "特权"+name+"添加成功，价格为"+price);
	}

	@Override
	public PrivilegeResponse getPrivilege(String name) throws NotExistException {
		return new PrivilegeResponse(new PrivilegeItem(privilegeDataService.getPrivilegeByName(name)));
	}

	@Override
	public PrivilegeListResponse getPrivilegeList() {
		List<Privilege> privileges = privilegeDataService.getAllPrivileges();
		List<PrivilegeItem> privilegeItems = new ArrayList<>();
		for (Privilege privilege : privileges) {
			privilegeItems.add(new PrivilegeItem(privilege));
		}
		return new PrivilegeListResponse(privilegeItems);
	}

	@Override
	public BoolResponse updatePrivilege(String name, int price) {
		Privilege privilege = null;
		try {
			privilege = privilegeDataService.getPrivilegeByName(name);
			privilege.setPrice(price);
			privilegeDataService.savePrivilege(privilege);
			return new BoolResponse(true, "特权"+name+"更新成功，价格为"+price);
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
	}

	@Override
	public BoolResponse deletePrivilege(String name) {
		try {
			privilegeDataService.deletePrivilegeByName(name);
			return new BoolResponse(true, "特权"+name+"已删除");
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
	}
}
