import { Component, OnInit } from '@angular/core';
import { DebtsChartService } from './debts-chart.service';
import { ChartMultiValue } from 'app/shared/chart/chart-multi-value';
import { HttpParams } from '@angular/common/http';
import { ChartValue } from 'app/shared/chart/chart-value';

@Component({
  selector: 'app-debts-and-loans',
  templateUrl: './debts-and-loans.component.html',
  styleUrls: ['./debts-and-loans.component.scss']
})
export class DebtsAndLoansComponent implements OnInit {

  toggle = false;
  selectedDebt = {}
  notes = ["Loading", "Loading", "Loading"]
  debts: any= ["Loading..."]
  multi: ChartMultiValue[] =  [
    {
      name: 'Loading...',
      series: [
        { name: new Date("2017-12-01"), value: 0 },
        { name: new Date("2018-01-01"), value: 1 },
        { name: new Date("2018-02-01"), value: 1 },
        { name: new Date("2018-03-01"), value: 2 },
        { name: new Date("2018-04-01"), value: 3 },
        { name: new Date("2018-05-01"), value: 5 },
        { name: new Date("2018-06-01"), value: 8 },
        { name: new Date("2018-07-01"), value: 13 },
        { name: new Date("2018-08-01"), value: 21 },
        { name: new Date("2018-09-01"), value: 34 },
        { name: new Date("2018-10-01"), value: 55 },
        { name: new Date("2018-11-01"), value: 89 },
        { name: new Date("2018-12-01"), value: 144 }
      ]
    }
  ];
  //view: any[] = [900,400];
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  showYAxisLabel = true;
  timeline = true;

  colorScheme = {
    domain: []
  };

  constructor(private debtChartService: DebtsChartService) { }

  fetchDebt(debt, params?){
    this.debtChartService.getDebt(debt.name, params).subscribe ( chart => {
        console.log("CART", chart)
        console.log("DETAILS", chart.details)
        chart.series.forEach (this.mapSeriesNameToDate)
        let burndown: ChartMultiValue = chart.details.trend;
       
        if(burndown){
          burndown.series.forEach(this.mapSeriesNameToDate)
        }
        this.multi = [chart, burndown];      
        this.colorScheme.domain[1] = 'yellow'
        this.notes = chart.details.trendNotes;
       
     })
  }


  ngOnInit() {
   this.debtChartService.getDebtItems().subscribe(res => {
      this.debts= res;
    })

    this.pickChartColors();
  }

  select (debt){
    this.selectedDebt = debt;
    this.toggle = !this.toggle
    this.fetchDebt(debt);
  }

  pickChartColors(){
    const colors = ['#B3BF5D', '#5DBF6C', '#5DA0BF', '#6C5DBF', '#BF5DA7', '#BF5D6C', '#86565E',
          '#86566F', '#785686', '#5B5686', '#567786', '#568668', '#7E8656' , '#AAC90E', '#0E6BC9'];
    
    for (let i = 0; i<5; i++){
      let index = Math.floor(Math.random() * colors.length);
      this.colorScheme.domain.push(colors[index]);
      colors.splice(index, 1);
    }
    
  }

  mapSeriesNameToDate = serie => serie.name = new Date(serie.name)

  calculate(amt){
    let params = new HttpParams().set("payment", amt.value);
    this.fetchDebt(this.selectedDebt, params);
  }
 

}
