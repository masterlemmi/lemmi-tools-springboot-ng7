import { SpokenLanguagesComponent } from './spoken-languages/spoken-languages.component';
import { BooksComponent } from './books/books.component';
import { MoviesComponent } from './movies/movies.component';


import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InterestsHomeComponent } from './home/interests-home.component';
import { MangaComponent } from './manga/manga.component';


const routes: Routes = [
  { path: '', component: InterestsHomeComponent, children: [ 
      { path: 'languages', component: SpokenLanguagesComponent}, 
      { path: 'books', component: BooksComponent },
      { path: 'movies', component: MoviesComponent },
      { path: 'manga', component: MangaComponent }
]},
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InterestsRoutingModule { }
