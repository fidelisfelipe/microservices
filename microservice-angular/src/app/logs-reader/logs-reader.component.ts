import { Component, OnInit, OnDestroy } from '@angular/core';
import {Message} from "@stomp/stompjs";
import {RxStompService} from "../services/rx-stomp.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-logs-reader',
  templateUrl: './logs-reader.component.html',
  styleUrls: ['./logs-reader.component.css']
})
export class LogsReaderComponent implements OnInit, OnDestroy {
  receivedMessages: string[] = [];

  private topicSubscription!: Subscription;

  constructor(private rxStompService: RxStompService) {}

  ngOnInit() {
    this.topicSubscription = this.rxStompService
      .watch('/topic/logs')
      .subscribe((message: Message) => {
        this.receivedMessages.push(message.body);
      });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  onSendMessage() {
    const message = `Message generated at ${new Date()}`;
    this.rxStompService.publish({ destination: '/topic/logs', body: message });
  }
}
