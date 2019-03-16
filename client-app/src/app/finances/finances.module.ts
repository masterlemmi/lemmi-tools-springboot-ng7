import { DebtsAndLoansComponent } from './debts-and-loans/debts-and-loans.component';
import { SharedModule } from 'app/shared/shared.module';
import { NgModule } from '@angular/core';
import { FinancesRoutingModule } from './finances-routing.module';
import { FinancesHomeComponent } from './finances-home/finances-home.component';
import { BudgetComponent } from './budget/budget.component';

@NgModule({
  declarations: [FinancesHomeComponent, DebtsAndLoansComponent, BudgetComponent],
  imports: [
    FinancesRoutingModule, SharedModule
  
  ],
  exports : [
    
  ],
  
  entryComponents: []
})
export class FinancesModule { }
