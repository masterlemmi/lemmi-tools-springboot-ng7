import { DebtsAndLoansComponent } from './debts-and-loans/debts-and-loans.component';
import { FinancesHomeComponent } from './finances-home/finances-home.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', component: FinancesHomeComponent, children: [ 
      { path: 'debts', component: DebtsAndLoansComponent},
      { path: 'budget', component: DebtsAndLoansComponent},
      { path: 'investments', component: DebtsAndLoansComponent} 
]},
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FinancesRoutingModule { }
