import { Component, OnInit } from '@angular/core';
import { LoginComponent } from "../../login/login.component";
import { ActivatedRoute, Router, RouterLink, RouterOutlet } from '@angular/router';
import { initFlowbite } from 'flowbite/lib/esm/components';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menuadministrador',
  standalone: true,
  templateUrl: './menuadministrador.component.html',
  styleUrl: './menuadministrador.component.scss',
  imports: [RouterOutlet, LoginComponent, LoginComponent, CommonModule, RouterLink]
})
export class MenuadministradorComponent implements OnInit {

  constructor(private activerouter: ActivatedRoute, private router: Router) {
    //this.dataSource.data = TREE_DATA;

  }


  factura() {
    this.router.navigate(['menuadministrador/factura']);

  }

  ngOnInit(): void {
    initFlowbite()
  }
  menuOption: string = '';

  onOption(menuOption: string) {
    this.menuOption = menuOption;
  }

}
