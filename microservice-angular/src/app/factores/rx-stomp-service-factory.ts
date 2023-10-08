import {RxStompService} from "../services/rx-stomp.service";
import {myRxStompConfig} from "../configs/my-rx-stomp.config";

export function rxStompServiceFactory() {
  const rxStomp = new RxStompService();
  rxStomp.configure(myRxStompConfig);
  rxStomp.activate();
  return rxStomp;
}
