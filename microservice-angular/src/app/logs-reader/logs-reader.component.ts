import { Component, OnInit, OnDestroy } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from "@stomp/stompjs";

@Component({
  selector: 'app-logs-reader',
  templateUrl: './logs-reader.component.html',
  styleUrls: ['./logs-reader.component.css']
})
export class LogsReaderComponent implements OnInit, OnDestroy {

  public logs: string[] = [];
  private stompService: StompService;

  constructor(stompService: StompService) {
    this.stompService = stompService;
  }

  ngOnInit() {
    this.stompService.initAndConnect();

    this.stompService.subscribe('/topic/logs').subscribe((message: Message) => {
      this.logs.push(message.body);
    });
  }

  ngOnDestroy() {
    this.stompService.disconnect();
  }
}
