import com.garmin.di.GameService;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Player;
import com.garmin.di.impl.GameServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/30
 * Time: 14:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class UnitTest {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private PlayerDao playerDao;

    @Test
    public void testIncreasePlayerExtraDistance() {
//        Player playerOne = playerDao.getPlayer("1");
        playerDao.increasePlayerExtraDistanceByEsn("1", 205);
    }

    @Test
    public void testBattleWithBoss() {
        GameService gameService = new GameServiceImpl(gameDao, playerDao);
        Pair<Integer, Integer> pair = gameService.battleWithBoss("1");
        System.out.println(pair.getLeft() + "/" + pair.getRight());
    }

    @Test
    public void testGetLinkedRoom() {
        GameService gameService = new GameServiceImpl(gameDao, playerDao);
        List<LinkedRoom> linkedRooms = gameService.getLinkedRoom("1");
        for (LinkedRoom linkedRoom: linkedRooms) {
            System.out.println(linkedRoom.getRoomId() + "/" + linkedRoom.getDistance());
        }
    }

}
