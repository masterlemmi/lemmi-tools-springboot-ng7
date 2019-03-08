import {Gender} from './gender';

export class Person {
    id: number
    gender: Gender
    profile: string
    photoUrl: string
    name: string
    nickname: string
    age: string
    dateOfBirth: Date
    dateOfDeath: Date
    parents: Person
    siblings: Person[]
    children: Person
    relationships: Map<string, Person[]>;
    deceased:boolean;
}
