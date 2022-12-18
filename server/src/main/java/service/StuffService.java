package service;

import command.stuffManagment.AddStuffCommand;
import command.stuffManagment.DeleteStuffCommand;
import command.stuffManagment.UpdateStuffCommand;
import database.dao.StuffDao;
import entity.Stuff;
import lombok.extern.slf4j.Slf4j;
import response.Response;
import response.stuffManagment.AddStuffResponse;
import response.stuffManagment.DeleteStuffResponse;
import response.stuffManagment.StuffListResponse;
import response.stuffManagment.UpdateStuffResponse;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class StuffService {
    private final StuffDao stuffDao;
    private static final StuffService stuffService = new StuffService();

    private StuffService() {
        stuffDao = StuffDao.getInstance();
    }

    public static StuffService getInstance() {
        return stuffService;
    }

    public Response getAllStuff() throws SQLException {
        List<Stuff> all = stuffDao.getAll();
        return new StuffListResponse(all);
    }
    public Response addStuff(AddStuffCommand command) throws SQLException {
        stuffDao.addStuff(command.getStuff());
        return new AddStuffResponse();
    }
    public Response deleteStuff(DeleteStuffCommand command) throws SQLException {
        stuffDao.deleteStuff(command.getStuff());
        return new DeleteStuffResponse();
    }
    public Response updateStuff(UpdateStuffCommand command) throws SQLException {
        stuffDao.updateStuff(command.getStuff());
        return new UpdateStuffResponse();
    }
}
