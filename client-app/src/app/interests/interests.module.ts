import { SharedModule } from 'app/shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InterestsHomeComponent } from './home/interests-home.component';
import { SpokenLanguagesComponent } from './spoken-languages/spoken-languages.component';
import { BooksComponent } from './books/books.component';
import { MangaComponent } from './manga/manga.component';
import { MoviesComponent } from './movies/movies.component';
import { InterestsRoutingModule } from './interests-routing.module';
import { TranslationsComponent } from './spoken-languages/translations/translations.component';

@NgModule({
  declarations: [InterestsHomeComponent, SpokenLanguagesComponent, BooksComponent, MangaComponent, MoviesComponent, TranslationsComponent],
  imports: [
    InterestsRoutingModule, SharedModule
  
  ],
  exports : [
    
  ],
  
  entryComponents: [TranslationsComponent]
})
export class InterestsModule { }
