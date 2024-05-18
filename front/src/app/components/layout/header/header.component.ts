import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterLink, RouterOutlet } from '@angular/router';
import { initFlowbite } from 'flowbite';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

  constructor(private activerouter: ActivatedRoute, private router: Router) {
    //this.dataSource.data = TREE_DATA;

  }


ngOnInit(): void {
    initFlowbite()
}
menuOption: string ='';

onOption(menuOption: string){
  this.menuOption = menuOption;
}

factura(){
  this.router.navigate(['/factura']);

}
}
