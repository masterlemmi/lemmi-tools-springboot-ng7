import { Component, OnInit } from '@angular/core';
import { DebtsChartService } from './debts-chart.service';
import { ChartMultiValue } from 'app/shared/chart/chart-multi-value';

@Component({
  selector: 'app-debts-and-loans',
  templateUrl: './debts-and-loans.component.html',
  styleUrls: ['./debts-and-loans.component.scss']
})
export class DebtsAndLoansComponent implements OnInit {

  debtData: ChartMultiValue[] = [];

  constructor(private debtChartService: DebtsChartService) { }

  ngOnInit() {
    this.debtChartService.getAll().subscribe ( res => {
      console.log("chart data", res)
      this.debtData = res;
    })
  }

}
