import { DebtsAndLoansComponent } from './debts-and-loans/debts-and-loans.component';
import { SharedModule } from 'app/shared/shared.module';
import { NgModule } from '@angular/core';
import { FinancesRoutingModule } from './finances-routing.module';
import { FinancesHomeComponent } from './finances-home/finances-home.component';
import { BudgetComponent } from './budget/budget.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';

@NgModule({
  declarations: [FinancesHomeComponent, DebtsAndLoansComponent, BudgetComponent],
  imports: [
    FinancesRoutingModule, SharedModule,NgxChartsModule
  
  ],
  exports : [
    
  ],
  
  entryComponents: []
})
export class FinancesModule { }
