import { RxStompConfig } from '@stomp/rx-stomp';




export const myRxStompConfig: RxStompConfig = {
  brokerURL: 'ws://localhost:8200/websocket-logs',
  //brokerURL: 'ws://fidelisdev-workflow.azurewebsites.net/websocket-logs',
  // ... outras configurações, se necessário
  // Headers
  connectHeaders: {
    login: '',
    passcode: '',
  },

  // How often to heartbeat?
  heartbeatIncoming: 0, // Typical value 0 - disabled
  heartbeatOutgoing: 20000, // Typical value 20000 - every 20 seconds

  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  reconnectDelay: 200,

  debug: (msg: string): void => {
    console.log(new Date(), msg);
  },


};
