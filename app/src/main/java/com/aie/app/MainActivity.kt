package com.aie.app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aie.app.database.entity.Employee
import com.aie.app.model.EmployeeWithDept
import com.aie.app.viewModel.EmployeeVM
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.employee_list_item.*
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {

    private var employeeVM: EmployeeVM? = null
    private var employeeListAdapter: EmployeeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeVM = ViewModelProvider(this).get(EmployeeVM::class.java)

        val layoutManager = LinearLayoutManager(this)
        rv_employees!!.layoutManager = layoutManager

        employeeVM!!.allEmployees.observe(this, Observer {

            Log.e("", "")
            employeeListAdapter = EmployeeListAdapter(
                this,
                it.toList(),)
            rv_employees!!.adapter = employeeListAdapter
        })
    }


    private inner class EmployeeListAdapter(
        internal var context: Context,
        internal var employeeList: List<EmployeeWithDept>
    ) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeListViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): EmployeeListViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.employee_list_item, parent, false)
            return EmployeeListViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: EmployeeListViewHolder,
            position: Int
        ) {
            val employee: EmployeeWithDept = employeeList.get(position)

            if(employee.employee.id > 0) {
                holder.tvId.text = employee.employee.id.toString()
            }

            if(employee.employee.emp_name != null) {
                holder.tvName.text = employee.employee.emp_name
            }

            if(employee.employee.joining_date != null) {
                holder.tvJoinDate.text = employee.employee.joining_date
            }

            if(employee.employee.manager_id!! > 0) {
                holder.tvManagerId.text = employee.employee.manager_id.toString()
            }

            if(employee.department.isNotEmpty()) {
                holder.tvDepartmentId.text = employee.department[0].name
            }
        }

        override fun getItemCount(): Int {
            return employeeList.size
        }

        inner class EmployeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            internal var tvId: TextView
            internal var tvName: TextView
            internal var tvJoinDate: TextView
            internal var tvDepartmentId: TextView
            internal var tvManagerId: TextView

            init {
                tvId = itemView.findViewById(R.id.tv_id)
                tvName = itemView.findViewById(R.id.tv_name)
                tvJoinDate = itemView.findViewById(R.id.tv_date)
                tvDepartmentId = itemView.findViewById(R.id.tv_department)
                tvManagerId = itemView.findViewById(R.id.tv_manager)
            }
        }
    }
}