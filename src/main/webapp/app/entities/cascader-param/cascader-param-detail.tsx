import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cascader-param.reducer';
import { ICascaderParam } from 'app/shared/model/cascader-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICascaderParamDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CascaderParamDetail = (props: ICascaderParamDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cascaderParamEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          CascaderParam [<b>{cascaderParamEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="paramTitle">Param Title</span>
          </dt>
          <dd>{cascaderParamEntity.paramTitle}</dd>
          <dt>
            <span id="peckOrder">Peck Order</span>
          </dt>
          <dd>{cascaderParamEntity.peckOrder}</dd>
          <dt>
            <span id="levelIndex">Level Index</span>
          </dt>
          <dd>{cascaderParamEntity.levelIndex}</dd>
          <dt>Parent</dt>
          <dd>{cascaderParamEntity.parentId ? cascaderParamEntity.parentId : ''}</dd>
          <dt>Cascader</dt>
          <dd>{cascaderParamEntity.cascaderCascaderName ? cascaderParamEntity.cascaderCascaderName : ''}</dd>
        </dl>
        <Button tag={Link} to="/cascader-param" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cascader-param/${cascaderParamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cascaderParam }: IRootState) => ({
  cascaderParamEntity: cascaderParam.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CascaderParamDetail);
