import { Injectable, Output, EventEmitter } from '@angular/core';
import { ModalData } from '../models/modal-data';
import { Person } from '../models/person';

export class PersonSearchModalService {
  isOpen = false;

  @Output() change: EventEmitter<ModalData> = new EventEmitter();

  toggle(people: Person[], title: string) {
    this.isOpen = !this.isOpen;
    this.change.emit({isOpen: this.isOpen, data: people, title: title});
  }
}
